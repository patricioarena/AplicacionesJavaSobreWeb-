export default class Requirement {
    constructor(
        _id,
        _idRequestPerson,
        _idTypeJob,
        _idRequirementStatus,
        date,
        address,
        description,
        zone) {

        this._id = _id;
        this._idRequestPerson = _idRequestPerson;
        this._idTypeJob = _idTypeJob;
        this._idRequirementStatus = _idRequirementStatus;
        this.date = date;
        this.address = address;
        this.description = description;
        this.zone = zone;

    }

}
