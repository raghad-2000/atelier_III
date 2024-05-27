<script lang="ts">
	import Card from '$lib/Card.svelte';
	import Header from '$lib/Header.svelte';
	import { onMount } from 'svelte';

	import { isUserConnected } from '../services/userService';
	import { browser } from '$app/environment';
</script>

<Header />
{#if !isUserConnected()}
	<div role="alert" class="alert alert-warning">
		<svg
			xmlns="http://www.w3.org/2000/svg"
			class="stroke-current shrink-0 h-6 w-6"
			fill="none"
			viewBox="0 0 24 24"
			><path
				stroke-linecap="round"
				stroke-linejoin="round"
				stroke-width="2"
				d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"
			/></svg
		>
		<span>Warning: You must be connected to use this application!</span>
	</div>
{/if}

<div class="card-wrapper">
	<div class="card">
		<Card
			title="Buy"
			description="Buy cards"
			buttonText="Buy now!"
			redirectLink="buy"
			disabled={!isUserConnected()}
		/>
	</div>
	<div class="card">
		<Card
			title="Market"
			description="Sell cards"
			buttonText="Sell now!"
			redirectLink="sell"
			disabled={!isUserConnected()}
		/>
	</div>
	<div class="card">
		<Card
			title="Play"
			description="Play with cards"
			buttonText="Play now!"
			disabled={!isUserConnected()}
		/>
	</div>
</div>

<style>
	.card-wrapper {
		display: flex;
		flex-wrap: wrap;
		width: 100%;
		justify-content: center;
		gap: 5em;
		margin-top: 5em;
	}
</style>
