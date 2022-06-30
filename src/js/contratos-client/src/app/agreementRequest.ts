import { AgreementDurationType } from "./agreementDurationType";

export class AgreementRequest {
    instituteId: number;
    categoryId: string;
    points: number;
    initialDate: String;
    endDate: String;
    durationType: AgreementDurationType;
    accepted: boolean;

    constructor() {
        this.instituteId = 0;
        this.categoryId = '';
        this.points = 0;
        this.initialDate = '';
        this.endDate = '';
        this.durationType = AgreementDurationType.SHORT;
        this.accepted = true;
    }
}

