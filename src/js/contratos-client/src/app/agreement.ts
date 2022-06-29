import { Institute } from "./institute";
import { Category } from "./category";

export class Agreement {
    id: number;
    institute: Institute;
    category: Category;
    points: number;
    duration: String;
    initialDate: String;
    endDate: String;
    assignedDate: String;

    constructor() {
        this.id = 0;
        this.institute = new Institute();
        this.category = new Category();
        this.points = 0;
        this.duration = '';
        this.initialDate = '';
        this.endDate = '';
        this.assignedDate = '';
    }
}

