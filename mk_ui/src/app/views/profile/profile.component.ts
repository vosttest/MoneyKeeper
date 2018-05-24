import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { UserProvider } from '../../providers/provider';


@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
    public vm: any = {};
    public loader: boolean;
    public type = 'MAIL';

    @ViewChild('activeCodePopup') public activeCodePopup: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.view();
    }

    public save() {
        this.loader = true;

        this.pro.save(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/dashboard']);
            } else {
                //TODO - Show popup
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public getActiveCode() {
        this.pro.getActiveCode(this.type).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.activeCodePopup.hide();
            } else {
                //TODO - Show popup
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private view() {
        this.loader = true;
        this.pro.view().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.vm = rsp.result;
            } else {
                //TODO - Show popup
                console.log(rsp.message);
            }
            this.loader = false;
        }, err => console.log(err));
    }
}