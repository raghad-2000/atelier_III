<script>
	import { browser } from '$app/environment';
	import 'tailwindcss/tailwind.css';
</script>

{#if browser}
	<slot />
{/if}
