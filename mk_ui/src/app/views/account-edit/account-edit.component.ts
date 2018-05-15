import { Component, OnInit } from '@angular/core';
import { CommonProvider } from '../../providers/provider';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { ActivatedRoute } from '@angular/router';

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
    public selected = '';
    public selectedCurrency = '';
    public selectedTerm = '';
    public selectedTermEnd = '';
    public selectedInterestPaid = '';
    public pickSaveAcc = false;
    public pickAtm = false;
    public pickOther = false;

    constructor(private pro: CommonProvider, private route: ActivatedRoute) { }

    ngOnInit() {
        const id = +this.route.snapshot.paramMap.get('id');
        console.log(id);

        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');

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
        }
        else if (va === "ACC03") {
            this.pickSaveAcc = true;
            this.pickAtm = false;
        } else {
            this.pickSaveAcc = true;
            this.pickAtm = true;
            this.pickOther = false;
        }
    }
}