<script lang="ts">
	import { redirectToUrl } from '../../services/redirectService';
	import CardPal from '$lib/CardPal.svelte';
	import Header from '$lib/Header.svelte';
	import { onMount, setContext } from 'svelte';
	import { Card } from '../../models/Card';

	setContext('buy', { buyCard });

	let successfulTransaction: boolean = false;
	let username: string = '';
	let money: number = 0;
	function sortById(cardArray: Card[]) {
		return cardArray.sort((a, b) => a.palId - b.palId);
	}
	async function buyCard(card: Card) {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/buy', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				authorization: `Bearer ${token}`
			},
			body: JSON.stringify(card)
		});
		document.getElementById('my_modal_1').showModal();
		console.log(result.status);
		switch (result.status) {
			case 401:
				sessionStorage.clear();
				redirectToUrl('/');
				break;
			case 200:
				// OK
				await getUserInfos();
				successfulTransaction = true;
				break;
			case 500:
				// NOT OK
				successfulTransaction = false;
				break;
			default:
				break;
		}
		money -= card.price;
	}

	let cardsSorted: Card[] = [];

	onMount(async () => {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/cards', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				authorization: `Bearer ${token}`
			}
		});
		if (result.status === 401) {
			sessionStorage.clear();
			redirectToUrl('/');
		} else {
			const data = await result.json();
			cardsSorted = data;
		}
		let userInfos = await getUserInfos();
	});

	async function getUserInfos() {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/user/infos', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				authorization: `Bearer ${token}`
			}
		});
		if (result.status === 401) {
			sessionStorage.clear();
			redirectToUrl('/');
			return;
		}
		console.log(result);
		const data = await result.json();
		username = data.username;
		money = data.money;
		return data;
	}
</script>

<Header />
<div class="flex flex-col w-full border-opacity-50">
	<div class="divider">{username}'s sold : {money}$</div>
</div>
<div class="card-wrapper">
	<div class="gallerie">
		{#each cardsSorted as item}
			<CardPal card={item} />
		{:else}
			<!-- si 0 cartes found -->
			<span class="loading loading-spinner loading-lg"></span>
		{/each}
	</div>
</div>

<dialog id="my_modal_1" class="modal">
	{#if !successfulTransaction}
		<!-- not enough cash -->
		<div class="modal-box">
			<h3 class="font-bold text-lg">Sorry!</h3>
			<p class="py-4">You're too poor.</p>
			<div class="modal-action">
				<form method="dialog">
					<button class="btn">Close</button>
				</form>
			</div>
		</div>
	{:else}
		<div class="modal-box">
			<h3 class="font-bold text-lg">Thank you!</h3>
			<p class="py-4">Congratulation on your purchase! This Pal is now in your profile.</p>
			<div class="modal-action">
				<form method="dialog">
					<button class="btn">Close</button>
				</form>
			</div>
		</div>
	{/if}
</dialog>

<style>
	.card-wrapper {
		display: flex;
		gap: 1em;
		justify-content: center;
		flex-wrap: wrap;
		margin: 1em;
		height: 100%;
	}
	.gallerie {
		display: flex;
		height: fit-content;
		gap: 2em;
		flex-wrap: wrap;
		justify-content: center;
	}
</style>
