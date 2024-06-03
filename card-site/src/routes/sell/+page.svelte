<!-- display user Info -->

<script lang="ts">
	import CardPalSell from './../../lib/CardPalSell.svelte';
	import CardPalInfo from './../../lib/CardPalInfo.svelte';
	import Header from './../../lib/Header.svelte';
	import { onMount, setContext } from 'svelte';
	import { redirectToUrl } from '../../services/redirectService';
	import type { Card } from '../../models/Card';
	import { invalidateAll } from '$app/navigation';

	let username: string = '';
	let money: number = 0;
	let userCards: any[] = [];

	let soldCard: any = null;
	setContext('sell', { sellCard });

	let successfulTransaction: boolean = false;

	async function sellCard(card: Card) {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/sell', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				authorization: `Bearer ${token}`
			},
			body: JSON.stringify(card)
		});
		switch (result.status) {
			case 401:
				sessionStorage.clear();
				redirectToUrl('/');
				break;
			case 200:
				// OK
				successfulTransaction = true;
				soldCard = card;
				break;
			case 403:
				break;
			default:
				break;
		}
		document.getElementById('my_modal_1').showModal();
	}

	onMount(async () => {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/user/cards', {
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
		const data = await result.json();
		console.log(data);
		userCards = data.cards;
		username = data.username;
		money = data.money;
	});

	function removeOneCard(card: Card) {
		console.log(userCards);
		console.log(card);
		let count = 0;
		userCards.forEach((item) => {
			if (item.id === card.id) {
				if (item.quantity - 1 <= 0) {
					// delete card
					userCards.splice(count, 1);
				} else {
					userCards[count].quantity -= 1;
				}
			}
			count += 1;
		});
		userCards = userCards;
		money += card.price;
	}
</script>

<Header />
<div class="flex flex-col w-full border-opacity-50">
	<div class="divider">{username}'s sold : {money}$</div>
	<div class="divider">{username}'s cards</div>
</div>
<div class="card-wrapper">
	<div class="gallerie">
		{#each userCards as card}
			<CardPalSell bind:card bind:quantity={card.quantity} />
		{:else}
			<!-- si 0 cartes found -->
			<span class="loading loading-spinner loading-lg"></span>
		{/each}
	</div>
</div>

<dialog id="my_modal_1" class="modal" on:close={() => removeOneCard(soldCard)}>
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
			<h3 class="font-bold text-lg">Done!</h3>
			<p class="py-4">This card is no longer in your possession.</p>
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
