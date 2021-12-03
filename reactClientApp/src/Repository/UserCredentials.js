export class UserCredentials {
    constructor(
        _idRole,
        name,
        lastName,
        birthDate,
        typeDoc,
        numberDoc,
        gender,
        email,
        telephoneNumber,
        reputation,
        address
    ) {
        this._idRole = _idRole;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.typeDoc = typeDoc;
        this.numberDoc = numberDoc;
        this.gender = gender;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.reputation = reputation;
        this.address = address;
    }

}


export class Address {
    constructor(
        street,
        number,
        zipCode,
        city,
        provState,
        country
    ) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.provState = provState;
        this.country = country;
    }

}
