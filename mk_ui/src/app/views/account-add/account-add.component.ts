import { Component, OnInit } from '@angular/core';
import { CommonProvider, AccountProvider } from '../../providers/provider';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { Router } from '@angular/router';

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
    public selected = '';
    public selectedCurrency = '';
    public selectedTerm = 'TRM02';
    public selectedTermEnd = '';
    public selectedInterestPaid = '';
    public pickSaveAcc = false;
    public pickAtm = false;
    public pickOther = false;
    public vm: any = { id: '' };
    public message = '';
    public account: any[] = [];

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    constructor(private proCommon: CommonProvider,
        private proAccount: AccountProvider,
        private rou: Router) { }

    ngOnInit() {
        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');
        this.search();
    }

    private getType(type: string) {
        this.proCommon.search(type).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                if (type == 'Account') {
                    this.lstType = rsp.result.data;
                } else if (type == 'Currency') {
                    this.lstCurrency = rsp.result.data;
                } else if (type == 'Term') {
                    this.lstTerm = rsp.result.data;
                } else if (type == 'InterestPaid') {
                    this.lstInterestPaid = rsp.result.data;
                } else if (type == 'TermEnd') {
                    this.lstTermEnd = rsp.result.data;
                }
            }
        }, err => console.log(err));
    }

    public checkType(va: string) {
        console.log(va);
        if (va === "ACC05") {
            this.pickSaveAcc = false;
            this.pickOther = true;
            this.pickAtm = true;
        }
        else if (va === "ACC03") {
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
        console.log(this.vm);
        this.proAccount.save(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/account']);
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err))
    }

    private search() {
        let obj = { keyword: '' };
        this.proAccount.search(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;

                console.log(this.account);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}