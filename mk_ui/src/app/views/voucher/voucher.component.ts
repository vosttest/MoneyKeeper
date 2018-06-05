import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import {
    ExpenseProvider,
    VoucherProvider,
    IncomeProvider,
    AccountProvider
} from '../../providers/provider';
import { ModalDirective, DateFormatter } from 'ngx-bootstrap';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-voucher',
    templateUrl: './voucher.component.html',
    styleUrls: ['./voucher.component.css', '../category/category.component.css']
})

export class VoucherComponent implements OnInit {
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

    public vm: any = { keyword: "", type: "Expense", total: null, accountId: null, description: " ", payee: " ", payer: " ", transferFee: null, toAccount: null, startDate: new Date() };
    public selectedCategory = { code: "", text: "-- Please Select --", icon: "" };
    public selectedAccount = { accountId: 0, text: "-- Please Select --" };
    public selectedToAccount = { accountId: 0, text: "-- Please Select --" };

    public isCheck: boolean;
    public isExpense: boolean = false;
    public isIncome: boolean = false;
    public isTransfer: boolean = false;
    public isAdjustment: boolean = false;
    public loader: boolean = false;


    public apiURL: string = "../../../assets/img/";
    public labelObj: string = "";
    public message = "";
    public function = "overview";
    public msg = "";
    public success = false;
    // public isShow: boolean = true;

    public labelAccountId = "Account";

    @ViewChild("categoryModal") public categoryModal: ModalDirective;
    @ViewChild("accountModal") public accountModal: ModalDirective;
    @ViewChild("toAccountModal") public toAccountModal: ModalDirective;
    @ViewChild("informationModal") public informationModal: ModalDirective;

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    // bsValue: Date = new Date();

    constructor(private proExpense: ExpenseProvider,
        private proIncome: IncomeProvider,
        private proAccount: AccountProvider,
        private proVoucher: VoucherProvider,
        private rou: Router,
        private act: ActivatedRoute) { }

    ngOnInit() {
        this.getVoucher();
        this.getExpense();
        this.getAccount();
        this.hideShow("Expense");
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
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.lstParent = this.lstParentTmp = rsp.result.parent;
                this.lstChild = this.lstChildTmp = rsp.result.child;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public getAccount() {
        this.proAccount.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.lstAccount = rsp.result.data;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public getIncome() {
        this.proIncome.search().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.lstParent = this.lstParentTmp = rsp.result.parent;
                this.lstChild = this.lstChildTmp = rsp.result.child;
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public getVoucher() {
        this.proVoucher.search(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.voucher = rsp.result.data;
            } else {
                this.message = rsp.message;
                console.log(this.voucher)
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
        if (typeVoucher === "Expense") {
            this.getExpense();
        } else if (typeVoucher === "Income") {
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

    public hideShow(a: any) {
        if (a == "Expense") {
            this.getExpense();
            this.isExpense = true;
            this.isIncome = false;
            this.isTransfer = false;
            this.isAdjustment = false;
            this.labelAccountId = "Account";
        }
        else if (a == "Income") {
            this.getIncome();
            this.isIncome = true;
            this.isExpense = false;
            this.isTransfer = false;
            this.isAdjustment = false;
            this.labelAccountId = "To Account";
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
        }

        console.log(a);
    }

    private searchAccount() {

        this.proAccount.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private searchToAccount() {
        this.proAccount.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.toAccount = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public save() {
        console.log(this.vm);

        if (this.vm.type == "Transfer") {
            if (this.selectedCategory.code === "" || this.selectedAccount.accountId === 0 || this.selectedToAccount.accountId === 0) {
                return;
            }
        }
        else {
            if (this.selectedCategory.code === "" || this.selectedAccount.accountId === 0) {
                return;
            }
        }
        let t = this.vm.type === "Transfer" ? this.selectedToAccount.accountId : this.vm.object;
        let obj = {
            id: 0,
            description: this.vm.description,
            accountId: this.selectedAccount.accountId,
            payee: this.vm.payee,
            payer: this.vm.payer,
            toAccount: this.vm.toAccount,
            total: this.vm.total,
            transferFee: this.vm.transferFee,
            type: this.vm.type,
            category: this.selectedCategory.code,
            startDate: this.vm.startDate
        };
        this.loader = true;

        this.proVoucher.save(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msg = "Save successfully!";
                this.success = true;
            } else {
                this.msg = rsp.message;
                this.success = false;
            }
            this.informationModal.show();

            this.loader = false;
        }, err => console.log(err));
    }
}