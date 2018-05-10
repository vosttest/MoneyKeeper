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
    public lstParent = [];
    public lstChild = [];

    constructor(private proIncome: IncomeProvider,
        private proExpense: ExpenseProvider) { }

    ngOnInit() {
    }

    public searchIncome() {
        this.proIncome.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParent = rsp.result.parent;
                this.lstChild = rsp.result.child;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public searchExpense() {
        this.proExpense.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParent = rsp.result.parent;
                this.lstChild = rsp.result.child;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private redirectAdd()
    {
        document.getElementById("divAdd").style.display="block";
        document.getElementById("divCategory").style.display="none";
    }

    private redirectCategory()
    {
        document.getElementById("divAdd").style.display="none";
        document.getElementById("divCategory").style.display="block";
    }
}