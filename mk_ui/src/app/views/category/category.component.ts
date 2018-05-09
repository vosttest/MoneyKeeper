import { Component, OnInit } from '@angular/core';
import {
    UserProvider,
    IncomeProvider,
    ExpenseProvider
} from '../../providers/provider';

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
    public lstParentIncome = [];
    public lstChildIncome = [];

    public lstParentExpense = [];
    public lstChildExpense = [];

    constructor(private proIncome: IncomeProvider,
        private proExpense: ExpenseProvider) { }

    ngOnInit() {
        this.searchIncome();
        this.searchExpense();
    }

    private searchIncome() {
        this.proIncome.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParentIncome = rsp.result.parent;
                console.log(this.lstParentIncome);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private searchExpense() {
        this.proExpense.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParentExpense = rsp.result.parent;
                this.lstChildExpense = rsp.result.child;
                console.log(rsp);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}