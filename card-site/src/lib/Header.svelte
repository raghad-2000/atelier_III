<script lang="ts">
	import { redirectToUrl } from '../services/redirectService';
	import { isUserConnected, logOut } from '../services/userService';
</script>

<div class="navbar bg-base-100 header-wrapper">
	<div class="flex-1">
		<a href="/" class="btn btn-ghost text-xl">Pal Trading Site</a>
	</div>
	<div class="flex-none user-button-wrapper">
		<ul class="menu menu-horizontal px-1">
			<li>
				<details>
					<summary>
						<!-- Icon not working -->
						<i data-feather="circle"></i> User
					</summary>
					<ul class="p-2 bg-base-100 rounded-t-none buttons-sidebar">
						{#if isUserConnected()}
							<li><a href="/profile">Profile</a></li>
							<li><p on:click={() => logOut()}>Log Out</p></li>
						{:else}
							<li><a href="/login">Login</a></li>
							<li><a href="/signup">Register</a></li>
						{/if}
					</ul>
				</details>
			</li>
		</ul>
	</div>
</div>

<style>
	.header-wrapper {
		background-color: #d3d3d3;
		border-top: solid;
		border-bottom: solid;
	}

	.user-button-wrapper {
		margin-right: 1em;
	}

	.buttons-sidebar {
		z-index: 999;
	}
</style>
