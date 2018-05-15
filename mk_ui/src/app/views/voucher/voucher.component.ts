import { Component, OnInit, ViewChild } from '@angular/core';
import {
    ExpenseProvider,
    VoucherProvider,
    IncomeProvider,
    AccountProvider
} from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-voucher',
    templateUrl: './voucher.component.html',
    styleUrls: ['./voucher.component.css', '../category/category.component.css']
})

export class VoucherComponent implements OnInit {
    public lstParent = [];
    public lstChild = [];
    public lstParentTmp = [];
    public lstChildTmp = [];
    public lstAccount = [];

    public selectedType = 'Expense';
    public selectedCategory = { code: '', text: '-- Select Category --' };
    public selectedAccount = { code: '', text: '-- Select Account --' };
    public message = '';

    @ViewChild('categoryModal') public categoryModal: ModalDirective;
    @ViewChild('accountModal') public accountModal: ModalDirective;

    constructor(private proExpense: ExpenseProvider,
        private proIncome: IncomeProvider,
        private proAccount: AccountProvider,
        private proVoucher: VoucherProvider) { }

    ngOnInit() {
        this.getExpense();
        this.getAccount();
    }

    public getExpense() {
        this.proExpense.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParent = this.lstParentTmp = rsp.result.parent;
                this.lstChild = this.lstChildTmp = rsp.result.child;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public getAccount() {
        let obj = { keyword: '' };
        this.proAccount.search(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstAccount = rsp.result.data;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public getIncome() {
        this.proIncome.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstParent = this.lstParentTmp = rsp.result.parent;
                this.lstChild = this.lstChildTmp = rsp.result.child;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public changeIcon(id: any) {
        let arrow = document.getElementById("arrow" + id).style.transform;

        if (arrow == "") {
            document.getElementById("arrow" + id).style.transform = "rotate(180deg)";
        }
        else {
            document.getElementById("arrow" + id).style.transform = null;
        }
    }

    public chooseCategory(code: string, text: string) {
        this.selectedCategory.code = code;
        this.selectedCategory.text = text;
        this.categoryModal.hide();
    }

    public changeType(typeVoucher: string) {
        if (typeVoucher === 'Expense') {
            this.getExpense();
        } else if (typeVoucher === 'Income') {
            this.getIncome();
        }
    }

    public search(keyword: string) {
        keyword = keyword.toLowerCase();
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