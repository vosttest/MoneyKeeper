import { Component, OnInit, ViewChild } from '@angular/core';
import { ExpenseProvider, VoucherProvider, IncomeProvider } from '../../providers/provider';
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
    public message = '';
    public selected = { code: '', text: '-- Select Category --' };

    @ViewChild('categoryModal') public categoryModal: ModalDirective;

    constructor(private proExpense: ExpenseProvider,
        private proIncome: IncomeProvider,
        private proVoucher: VoucherProvider) { }

    ngOnInit() {
        this.getExpense();
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

    public chooseCategoryModal() {
        this.categoryModal.show();
    }

    public chooseCategory(code: string, text: string) {
        this.selected.code = code;
        this.selected.text = text;
        this.categoryModal.hide();
    }
}