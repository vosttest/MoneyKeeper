import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/providers';
import { IncomeProvider } from '../../providers/income';
import { ExpenseProvider } from '../../providers/expense';

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
    public ltsParentIncome = [];
    public lstParentExpense = [];
    public lstChildExpense = [];

    constructor(private pro: IncomeProvider,
        private proExpense: ExpenseProvider) { }

    ngOnInit() {
        this.search();
        this.loadParentExpense();
    }

    public search() {
        this.pro.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.ltsParentIncome = rsp.result.parent;
                console.log(this.ltsParentIncome);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public loadParentExpense() {
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