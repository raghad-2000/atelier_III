export class Card {
	id: number;
	name: string;
	description: string;
	price: number;
	affinity: string;
	energy: number;
	hp: number;
	url: string;
	palId: number;

	constructor(
		id: number,
		name: string,
		description: string,
		price: number,
		affinity: string,
		energy: number,
		hp: number,
		url: string,
		palId: number
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.affinity = affinity;
		this.energy = energy;
		this.hp = hp;
		this.url = url;
		this.palId = palId;
	}
}
