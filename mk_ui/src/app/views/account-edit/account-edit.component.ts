import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { AccountProvider, CommonProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-account-edit',
    templateUrl: './account-edit.component.html',
    styleUrls: ['./account-edit.component.css']
})

export class AccountEditComponent implements OnInit {
    public lstType: any[] = [];
    public lstCurrency: any[] = [];
    public lstTerm: any[] = [];
    public lstInterestPaid: any[] = [];
    public lstTermEnd: any[] = [];
    public pickSaveAcc = false;
    public pickAtm = false;
    public pickOther = false;
    public vm: any = { id: "", type: "" };
    public message = "";
    public account: any[] = [];
    public loader: boolean = false;
    public msg = "";
    public routeLink = "";
    public isView: boolean = true;
    public id = 0;

    datePipe = new DatePipe("en");

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    @ViewChild("confirmModal") public confirmModal: ModalDirective;
    @ViewChild("informationModal") public informationModal: ModalDirective;

    constructor(private pro: CommonProvider,
        private rou: Router,
        private proAccount: AccountProvider,
        private route: ActivatedRoute) { }

    ngOnInit() {
        const param = this.route.snapshot.paramMap.get("id");
        let arr = param.split("-");
        this.id = +arr[1];
        if (arr[0] == "edit") {
            this.isView = false;
        }

        this.getType("Account");
        this.getType("Currency");
        this.getType("Term");
        this.getType("InterestPaid");
        this.getType("TermEnd");
        this.search();
        this.getAccount();
    }

    private getType(type: string) {
        this.pro.search(type).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                if (type == "Account") {
                    this.lstType = rsp.result.data;
                } else if (type == "Currency") {
                    this.lstCurrency = rsp.result.data;
                } else if (type == "Term") {
                    this.lstTerm = rsp.result.data;
                } else if (type == "InterestPaid") {
                    this.lstInterestPaid = rsp.result.data;
                } else if (type == "TermEnd") {
                    this.lstTermEnd = rsp.result.data;
                }
            }
        }, err => console.log(err));
    }

    public checkType(va: string) {
        if (va === "ACC005") {
            this.pickSaveAcc = false;
            this.pickOther = true;
        }
        else if (va === "ACC003") {
            this.pickSaveAcc = true;
            this.pickAtm = false;
        } else {
            this.pickSaveAcc = true;
            this.pickAtm = true;
            this.pickOther = false;
        }
    }

    public save() {
        this.loader = true;
        console.log(this.vm);

        this.proAccount.save(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msg = "Save successfully!";
                this.routeLink = "/account/overview";
            } else {
                this.routeLink = "";
                this.msg = rsp.message;
            }
            this.informationModal.show();

            this.loader = false;
        }, err => console.log(err))
    }

    private search() {
        this.proAccount.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public getAccount() {
        this.loader = true;

        this.proAccount.getAccount(this.id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.vm = rsp.result;

                if (this.vm.type === "ACC005") {
                    this.vm.startDate = new Date(this.vm.startDate);
                }
                console.log(this.vm.type);

                this.checkType(this.vm.type);
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public delete() {
        this.loader = true;

        this.proAccount.delete(this.id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msg = "Delete successfully!";
                this.routeLink = "/account/overview";
                this.confirmModal.hide();
            }
            else {
                this.msg = rsp.message;
                this.routeLink = "";
            }
            this.informationModal.show();

            this.loader = false;
        }, err => console.log(err));
    }
}