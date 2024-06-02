import { UserLogin } from '../models/UserLogin';

import { redirectToUrl } from '../services/redirectService';

export function buildPostRequest(object: Object, url: string, jwtoken: string): void {
	const xhr = new XMLHttpRequest();
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtoken);
	const body = JSON.stringify(object);
	xhr.onload = () => {
		if (xhr.readyState == 4 && xhr.status == 201) {
			console.log(JSON.parse(xhr.responseText));
		} else {
			console.log(`Error: ${xhr.status}`);
		}
	};
	xhr.send(body);
}

export function buildGetRequest(url: string, jwtoken: string): void {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url);
	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtoken);
	xhr.onload = () => {
		if (xhr.readyState == 4 && xhr.status == 201) {
			console.log(JSON.parse(xhr.responseText));
			return xhr.responseText;
		} else {
			console.log(`Error: ${xhr.status}`);
		}
	};
	xhr.send(null);
}

export async function loginRequest(login: Object, url: string, method: string) {
	const result = await fetch(url, {
		method: method,
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(login)
	});
	const data = await result.json();
	if (result.status === 403) {
		// invalid login
	} else {
		sessionStorage.setItem('token', data.token);
		redirectToUrl('/');
	}
}

export async function signUpRequest(login: Object, url: string, method: string) {
	const result = await fetch(url, {
		method: method,
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(login)
	});
	if (result.status === 403) {
		// invalid login
	} else {
		redirectToUrl('/');
	}
}
