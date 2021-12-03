export default class Address {
    constructor(
        street,
        number,
        zipCode,
        city,
        provState,
        country,
        coordinates) {

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.provState = provState;
        this.country = country;
        this.coordinates = coordinates;
    }
}