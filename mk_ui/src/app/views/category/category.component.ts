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
    public lstParentTmp = [];
    public lstChildTmp = [];
    public keyword= '';
    constructor(private proIncome: IncomeProvider,
        private proExpense: ExpenseProvider) { }

    ngOnInit() {
    }

    public loadIncome() {
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

    public loadExpense() {
        this.proExpense.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParent = rsp.result.parent;
                this.lstParentTmp = this.lstParent;
                this.lstChild = rsp.result.child;
                this.lstChildTmp = this.lstChild;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public redirectAdd()
    {
        document.getElementById("divAdd").style.display="block";
        document.getElementById("divCategory").style.display="none";
    }

    public redirectCategory()
    {
        document.getElementById("divAdd").style.display="none";
        document.getElementById("divCategory").style.display="block";
    }

    public search() {
        let keyword = this.keyword.toLowerCase();
        this.lstParentTmp = this.lstParent;
        this.lstChildTmp = this.lstChild;
        if (keyword != "" || keyword != null) {
            this.lstParentTmp = [];
            this.lstParent.forEach(obj => {
                let text = obj.text.toLowerCase();
                if (text.includes(keyword)) {
                    this.lstParentTmp.push(obj);
                }
            });
            this.lstChildTmp = [];
            this.lstChild.forEach(obj => {
                let text = obj.text.toLowerCase();
                if (text.includes(keyword)) {
                    this.lstChildTmp.push(obj);
                }
            });
        }
    }
}