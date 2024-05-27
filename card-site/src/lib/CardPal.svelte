<script lang="ts">
	import type { Card } from '../models/Card';
	// export permet de définir l'attribut depuis un composant parent.
	export let card: Card;

	import { getContext } from 'svelte';

	const { buyCard } = getContext('buy');
</script>

<div class="card w-96 bg-base-100 shadow-xl card-wrapper">
	<figure class="figure-pal"><img src={card.url} alt="img" /></figure>
	<div class="card-body">
		<h2 class="card-title">{card.name}</h2>
		<p>{card.description}</p>
		<div class="card-actions justify-end">
			<button class="btn btn-primary" on:click={() => buyCard(card)}>Buy for {card.price}$</button>
		</div>
	</div>
</div>

<style>
	.figure-pal {
		height: fit-content;
	}
	.card-wrapper {
		border: solid;
		padding: 1em;
	}
</style>
