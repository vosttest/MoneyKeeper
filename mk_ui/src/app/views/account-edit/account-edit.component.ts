import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { AccountProvider, CommonProvider } from '../../providers/provider';

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
    public vm: any = { id: '', type: '' };
    public message = '';
    public account: any[] = [];

    datePipe = new DatePipe("en");

    @ViewChild('confirmModal') public confirmModal: ModalDirective;

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    constructor(private pro: CommonProvider,
        private rou: Router,
        private proAccount: AccountProvider,
        private route: ActivatedRoute) { }

    ngOnInit() {
        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');
        this.search();
        this.getAccount();
    }

    private getType(type: string) {
        this.pro.search(type).subscribe((rsp: any) => {
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
        }
        else if (va === 'ACC003') {
            this.pickSaveAcc = true;
            this.pickAtm = false;
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

    public getAccount() {
        const id = +this.route.snapshot.paramMap.get('id');
        this.proAccount.getAccount(id).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.vm = rsp.result;
                this.vm.startDate = this.datePipe.transform(this.vm.startDate, 'yyyy-MM-dd');

                this.checkType(this.vm.type);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public delete() {
        const id = +this.route.snapshot.paramMap.get('id');
        this.proAccount.delete(id).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/account']);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}