<script lang="ts">
	import { getContext } from 'svelte';
	import type { Card } from '../models/Card';
	// export permet de définir l'attribut depuis un composant parent.
	export let card: Card;
	export let quantity: number;

	const { sellCard } = getContext('sell');

</script>

<div class="card w-96 bg-base-100 shadow-xl card-wrapper">
	<figure class="figure-pal"><img src={card.url} alt="img" /></figure>
	<div class="card-body">
		<h2 class="card-title title">
			{card.name}
			<div class="badge">x {quantity}</div>
		</h2>

		<p>{card.description}</p>
		<div class="card-actions justify-end">
			<button class="btn btn-primary" on:click={() => sellCard(card)}>Sell for {card.price}$</button>
		</div>
	</div>
</div>

<style>
	.title {
		justify-content: space-between;
	}
	.figure-pal {
		height: fit-content;
	}
	.card-wrapper {
		border: solid;
		padding: 1em;
	}
</style>
