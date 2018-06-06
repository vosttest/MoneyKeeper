import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { AccountProvider, CommonProvider, SettingProvider } from '../../providers/provider';
import { HTTP, Setting } from '../../utilities/utility';

@Component({
    selector: 'app-account-add',
    templateUrl: './account-add.component.html',
    styleUrls: ['./account-add.component.css']
})

export class AccountAddComponent implements OnInit {
    public lstType: any[] = [];
    public lstCurrency: any[] = [];
    public lstTerm: any[] = [];
    public lstInterestPaid: any[] = [];
    public lstTermEnd: any[] = [];
    public data: any = [];
    public pickSaveAcc = false;
    public pickAtm = false;
    public pickOther = false;
    public vm: any = { id: "", type: "ACC001", term: "TRM001", interestPaid: "IPD002", termEnded: "TRE002" };
    public message = "";
    public account: any[] = [];
    public msg = "";
    public success = false;
    public loader: boolean = false;

    @ViewChild("informationModal") public informationModal: ModalDirective;

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    constructor(private proCommon: CommonProvider,
        private proAccount: AccountProvider,
        private proSetting: SettingProvider,
        private rou: Router) { }

    ngOnInit() {
        this.getType("Account");
        this.getType("Currency");
        this.getType("Term");
        this.getType("InterestPaid");
        this.getType("TermEnd");
        this.searchCurrency();
        this.search();
        this.checkType(this.vm.type);
    }

    private getType(type: string) {
        this.proCommon.search(type).subscribe((rsp: any) => {
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
            this.pickAtm = true;
            this.vm.startDate = new Date();
        }
        else if (va === "ACC003") {
            this.pickSaveAcc = true;
            this.pickAtm = false;
            this.pickOther = false;
        } else {
            this.pickSaveAcc = true;
            this.pickAtm = true;
            this.pickOther = false;
        }
    }

    public save() {
        this.loader = true;

        this.proAccount.save(this.vm).subscribe((rsp: any) => {
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

    private search() {
        this.loader = true;

        this.proAccount.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }


    private searchCurrency() {
        this.proSetting.search().subscribe((rsp: any) => {
            if (rsp.status === "success") {
                this.data = rsp.result.data;
                console.log(this.data);
                this.data.forEach(element => {
                    if (element.code === Setting.CODE_CURRENCY) {
                        this.vm.currency = element.value === "" || element.value === null ? "VND" : element.value;
                    }
                });
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}