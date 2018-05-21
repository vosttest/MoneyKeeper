import { Component, OnInit } from '@angular/core';
import { AccountProvider } from '../../providers/provider';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
    public account = [];
    public vm: any = {};
    public expanded = false;
    public loader: boolean = false;

    constructor(private proAcc: AccountProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        let info = { keyword: '' };
        this.proAcc.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public showCheckboxes() {
        var checkboxes = document.getElementById("checkboxes");
        if (!this.expanded) {
            checkboxes.style.display = "block";
            this.expanded = true;
        } else {
            checkboxes.style.display = "none";
            this.expanded = false;
        }
    }
}
