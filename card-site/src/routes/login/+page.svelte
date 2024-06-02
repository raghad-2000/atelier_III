<script>
	import Form from '$lib/Form.svelte';
	import Header from '$lib/Header.svelte';
</script>

<Header />

<Form postUrl={'http://localhost:8085/login'} />
