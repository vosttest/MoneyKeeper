import { Component, OnInit } from '@angular/core';
import { CommonProvider } from '../../providers/provider';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';

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

    constructor(private pro: CommonProvider) { }

    ngOnInit() {
        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');
        console.log(this.selectedTerm);
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
    public testing(){
        console.log(this.selectedTerm);
    }
}