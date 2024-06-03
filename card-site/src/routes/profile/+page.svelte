<!-- display user Info -->

<script lang="ts">
	import CardPalInfo from './../../lib/CardPalInfo.svelte';
	import Header from './../../lib/Header.svelte';
	import { onMount } from 'svelte';
	let username: string = '';
	let userCards: any[] = [];

	onMount(async () => {
		const token = sessionStorage.getItem('token');
		const result = await fetch('http://localhost:8085/user/cards', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				authorization: `Bearer ${token}`
			}
		});
		const data = await result.json();
		userCards = data.cards;
		username = data.username;
	});
</script>

<Header />
<div class="flex flex-col w-full border-opacity-50">
	<div class="divider">{username}'s cards</div>
</div>
<div class="card-wrapper">
	<div class="gallerie">
		{#each userCards as card}
			<CardPalInfo {card} quantity={card.quantity} />
		{:else}
			<!-- si 0 cartes found -->
			<span>No cards found...</span>
			<span class="loading loading-spinner loading-lg"></span>
		{/each}
	</div>
</div>

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
