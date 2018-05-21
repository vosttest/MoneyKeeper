import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountProvider, CommonProvider } from '../../providers/provider';

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
    public pickSaveAcc = false;
    public pickAtm = false;
    public pickOther = false;
    public vm: any = { id: '', type: 'ACC001', currency: 'USD', term: 'TRM001', interestPaid: 'IPD002', termEnded: 'TRE002' };
    public message = '';
    public account: any[] = [];
    public loader: boolean = false;

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
        this.checkType(this.vm.type);
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
        if (va === 'ACC005') {
            this.pickSaveAcc = false;
            this.pickOther = true;
            this.pickAtm = true;
        }
        else if (va === 'ACC003') {
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
        this.proAccount.save(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/account']);
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err))
    }

    private search() {
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
}