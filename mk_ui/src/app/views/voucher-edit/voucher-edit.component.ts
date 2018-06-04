import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Const, HTTP, Type, Setting } from '../../utilities/utility';

import {
    ExpenseProvider,
    VoucherProvider,
    IncomeProvider,
    AccountProvider
} from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-voucher-edit',
  templateUrl: './voucher-edit.component.html',
  styleUrls: ['./voucher-edit.component.css']
})
export class VoucherEditComponent implements OnInit {
    public lstParent = [];
    public lstChild = [];
    public lstParentTmp = [];
    public lstChildTmp = [];
    public lstAccount = [];
    public lstSearch = [];
    public account = [];
    public voucher = [];
    public voucher1 = [];
    public voucherTmp = [];

    public vm: any = { type: 'Expense', total: null, accountId: null, description: " ", payee: " ", payer: " ", toAccount: null, startDate: new Date() };
    public cm: any = {};
    public date: any= {};
    public voucher2: any={};
    public selectedCategory = { code: '', text: '-- Select Category --', icon: '' };
    public selectedAccount = { accountId: 0, text: '-- Select Account --' };
    
    public isCheck: boolean;
    public isExpense: boolean = false;
    public isIncome: boolean = false;
    public isTransfer: boolean = false;
    public isAdjustment: boolean = false;
    public loader: boolean = false;
    public routeLink = '';
    public id = 0;

    public imgURL: string = "../../../assets/img/";
    public message = '';
    public labelObj: string = "";
    public function = "overview";
    public labelAccountId = 'Account';
    public msg = "";

    @ViewChild('categoryModal') public categoryModal: ModalDirective;
    @ViewChild('accountModal') public accountModal: ModalDirective;
    @ViewChild('confirmModal') public confirmModal: ModalDirective;
    @ViewChild('confirmDeleteModal') public confirmDeleteModal: ModalDirective;
    @ViewChild('informationModal') public informationModal: ModalDirective;

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

  constructor(private proExpense: ExpenseProvider,
    private proIncome: IncomeProvider,
    private proAccount: AccountProvider,
    private proVoucher: VoucherProvider,
    private rou: Router,
    private act: ActivatedRoute) { }

  ngOnInit() {
    const param = this.act.snapshot.paramMap.get('id');

    this.getExpense();
    this.getAccount();
    this.hideShow('Expense');
    this.searchAccount();
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

    public hideShow(a: any) {
        if (a == "Expense") {
            this.getExpense();
            this.isExpense = true;
            this.isIncome = false;
            this.isAdjustment = false;
            this.labelAccountId = "Account";
        }
        else if (a == "Income") {
            this.getIncome();
            this.isIncome = true;
            this.isExpense = false;
            this.isAdjustment = false;
            this.labelAccountId = "To Account";
        }
        else if (a == "Adjustment") {
            this.getIncome();
            this.isAdjustment = true;
            this.isIncome = false;
            this.isExpense = false;
            this.labelAccountId = "Account";
        }
    }

    private searchAccount() {
        this.proAccount.search('').subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public save() {
        if (this.vm.type == 'Transfer') {
            if (this.selectedCategory.code === '' || this.selectedAccount.accountId === 0) {
                return;
            }
        }
        else {
            if (this.selectedCategory.code === '' || this.selectedAccount.accountId === 0) {
                return;
            }
        }
        let obj = {
            id: 0,
            description: this.vm.description,
            accountId: this.selectedAccount.accountId,
            payee: this.vm.payee,
            payer: this.vm.payer,
            toAccount: this.vm.toAccount,
            total: this.vm.total,
            type: this.vm.type,
            category: this.selectedCategory.code,
            startDate: this.vm.startDate
        };

        this.proVoucher.save(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.confirmModal.show();
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public delete() {
        this.loader = true;

        const id = +this.act.snapshot.paramMap.get('voucherId');
        this.proAccount.delete(id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msg = 'Delete successfully!';
                this.routeLink = '/voucher/overview';
                this.confirmDeleteModal.hide();
            }
            else {
                this.msg = rsp.message;
                this.routeLink = '';
            }
            this.informationModal.show();

            this.loader = false;
            }, err => console.log(err));
        }
    }
