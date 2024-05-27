<script lang="ts">
	import { goto } from '$app/navigation';

	export let title: string = 'Card title';
	export let description: string = 'Card description';
	export let buttonText: string = 'Button Text';
	export let redirectLink: string = '';
	export let disabled: boolean = true;
	function redirectTo(url: string) {
		goto('/' + url);
	}
</script>

<div class="card w-96 bg-primary text-primary-content card-custom">
	<div class="card-body">
		<h2 class="card-title">{title}</h2>
		<p>{description}</p>
		<div class="card-actions justify-end">
			<button {disabled} class="btn" on:click={() => redirectTo(redirectLink)}>{buttonText}</button>
		</div>
	</div>
</div>

<style>
	.card-custom {
		background-color: #d3d3d3;
		color: black;
		border: solid;
	}
	.btn {
		border: solid;
	}
</style>
