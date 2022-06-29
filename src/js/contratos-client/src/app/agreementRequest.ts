export class AgreementRequest {
    instituteId: number;
    categoryId: string;
    points: number;
    initialDate: String;
    endDate: String;

    constructor() {
        this.instituteId = 0;
        this.categoryId = '';
        this.points = 0;
        this.initialDate = '';
        this.endDate = '';
    }
}

