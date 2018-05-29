import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

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
    public vm: any = { type: 'Expense', total: null, accountId: null, description: " ", object: " ", startDate: new Date() };
    public cm: any = {};
    public lstParent = [];
    public lstChild = [];
    public lstParentTmp = [];
    public lstChildTmp = [];
    public lstAccount = [];
    public lstSearch = [];
    public account = [];
    public toAccount = [];
    public voucher = [];
    public voucher1 = [];
    public voucherTmp = [];
    public voucher2: any;
    public apiURL: string = "../../../assets/img/";
    public loader: boolean = false;

    public selectedCategory = { code: '', text: '-- Select Category --', icon: '' };
    public selectedAccount = { accountId: 0, text: '-- Select Account --' };
    public selectedToAccount = { accountId: 0, text: '-- Select To Account --' };
    public message = '';
    public isCheck: boolean;
    public isExpense: boolean = false;
    public isIncome: boolean = false;
    public isTransfer: boolean = false;
    public isAdjustment: boolean = false;
    public labelObj: string = "";
    public function = "overview";
    public date: any;
    // public isShow: boolean = true;

    public labelAccountId = 'Account';

    @ViewChild('categoryModal') public categoryModal: ModalDirective;
    @ViewChild('accountModal') public accountModal: ModalDirective;
    @ViewChild('toAccountModal') public toAccountModal: ModalDirective;
    @ViewChild('confirmModal') public confirmModal: ModalDirective;

    // Datepicker

    maxDate = new Date();

    bsValue: Date = new Date();

    constructor(private proExpense: ExpenseProvider,
        private proIncome: IncomeProvider,
        private proAccount: AccountProvider,
        private proVoucher: VoucherProvider,
        private act: ActivatedRoute) { }

    ngOnInit() {
        this.getVoucher();
        this.getExpense();
        this.getAccount();
        this.hideShow('Expense');
        this.searchAccount();
        this.searchToAccount();

        this.act.params.subscribe((params: Params) => {
            document.getElementById(this.function).style.display = "none";
            this.function = params["function"];
            document.getElementById(this.function).style.display = "block";
        });
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

    public getVoucher() {
        this.proVoucher.search(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.voucher = rsp.result.data;
                this.voucher.forEach(obj => {
                    if (this.date != obj.startDate) {
                        this.date = obj.startDate;
                        let tmp = { startDate: obj.startDate, voucher: [] };
                        this.voucher2 = tmp.voucher;
                        this.voucher2.push(obj);
                        this.voucher1.push(tmp);
                    }
                    else {
                        this.voucher2.push(obj);
                    }
                });
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

    public chooseCategory(code: string, text: string, icon: string) {
        this.selectedCategory.code = code;
        this.selectedCategory.text = text;
        this.selectedCategory.icon = icon;
        this.categoryModal.hide();
    }

    public chooseAccount(accountId: any, text: string) {
        this.selectedAccount.accountId = accountId;
        this.selectedAccount.text = text;
        this.accountModal.hide();
    }

    public chooseToAccount(accountId: any, text: string) {
        this.selectedToAccount.accountId = accountId;
        this.selectedToAccount.text = text;
        this.toAccountModal.hide();
    }

    public changeType(typeVoucher: string) {
        if (typeVoucher === 'Expense') {
            this.getExpense();
        } else if (typeVoucher === 'Income') {
            this.getIncome();
        }
    }

    public searchVoucher(keyword: string) {
        if (keyword != "") {
            if (this.voucher1 != undefined || this.voucher1.length > 0) {
                for (let i = 0; i < this.voucher1.length; i++) {
                    let isParentNull = false;
                    let isChildNull = true;
                    for (let j = 0; j < this.voucher1[i].voucher.length; j++) {
                        let voucher = this.voucher1[i].voucher[j];
                        if (voucher.description.includes(keyword)
                            || voucher.type.includes(keyword)) {
                            isParentNull = true;
                        }

                        let details = this.voucher1[i].voucher[j].voucherDetail;
                        if (details != undefined) {
                            for (let z = 0; z < details.length; z++) {
                                if (!details[z].categoryText.includes(keyword)) {
                                    isChildNull = false;
                                    details.splice(0, 1);
                                    z=-1;
                                }
                            }
                        }

                        if (isParentNull && isChildNull) {
                            this.lstSearch.push(this.voucher1[j]);
                        }
                    }
                }
            }
        }
        else {
            this.voucher1= [];
            this.getVoucher();
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

    public hideShow(a: any) {
        if (a == "Expense") {
            this.getExpense();
            this.isExpense = true;
            this.isIncome = false;
            this.isTransfer = false;
            this.isAdjustment = false;
            this.labelAccountId = "Account";
            this.labelObj = "Payee";
        }
        else if (a == "Income") {
            this.getIncome();
            this.isIncome = true;
            this.isExpense = false;
            this.isTransfer = false;
            this.isAdjustment = false;
            this.labelAccountId = "To Account";
            this.labelObj = "Payer";
        }
        else if (a == "Transfer") {
            this.getExpense();
            this.isTransfer = true;
            this.isIncome = false;
            this.isExpense = false;
            this.isAdjustment = false;
            this.labelAccountId = "Account";
        }
        else if (a == "Adjustment") {
            this.getIncome();
            this.isAdjustment = true;
            this.isIncome = false;
            this.isExpense = false;
            this.isTransfer = false;
            this.labelAccountId = "Account";
            this.labelObj = "Payee";
        }

        console.log(a);
    }

    private searchAccount() {
        let info = { keyword: '' };
        this.proAccount.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private searchToAccount() {
        let info = { keyword: '' };
        this.proAccount.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.toAccount = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public save() {
        if (this.vm.type == 'Transfer') {
            if (this.selectedCategory.code === '' || this.selectedAccount.accountId === 0 || this.selectedToAccount.accountId === 0) {
                return;
            }
        }
        else {
            if (this.selectedCategory.code === '' || this.selectedAccount.accountId === 0) {
                return;
            }
        }
        let t = this.vm.type === "Transfer" ? this.selectedToAccount.accountId : this.vm.object;
        let obj = {
            id: 0, description: this.vm.description,
            accountId: this.selectedAccount.accountId,
            object: t,
            total: this.vm.total,
            type: this.vm.type,
            category: this.selectedCategory.code,
            startDate: this.vm.startDate
        };

        this.proVoucher.save(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.confirmModal.show();
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }
}