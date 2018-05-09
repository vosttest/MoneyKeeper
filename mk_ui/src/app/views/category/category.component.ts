import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/providers';
import { IncomeProvider } from '../../providers/income';

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
    public ltsParentIncome = [];

    constructor(private pro: IncomeProvider) { }

    ngOnInit() {
        this.search();
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
}