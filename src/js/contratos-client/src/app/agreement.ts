import { Institute } from "./institute";
import { Category } from "./category";
import { AgreementDurationType } from "./agreementDurationType";

export class Agreement {
    id: number;
    institute: Institute;
    category: Category;
    points: number;
    duration: String;
    initialDate: String;
    endDate: String;
    assignedDate: String;
    durationType: AgreementDurationType;
    accepted: boolean;

    constructor() {
        this.id = 0;
        this.institute = new Institute();
        this.category = new Category();
        this.points = 0;
        this.duration = '';
        this.initialDate = '';
        this.endDate = '';
        this.assignedDate = '';
        this.durationType = AgreementDurationType.SHORT;
        this.accepted = true;
    }
}

