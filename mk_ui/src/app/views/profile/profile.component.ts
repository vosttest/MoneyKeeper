import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { UserProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
    public vm: any = {};

    public loader: boolean;
    
    public type = "MAIL";
    public msg = "";

    @ViewChild("activeCodePopup") public activeCodePopup: ModalDirective;
    @ViewChild("informationModal") public informationModal: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.view();
    }

    public save() {
        this.loader = true;

        this.pro.save(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msg = "Save successfully!";
            } else {
                this.msg = rsp.message;
            }
            this.informationModal.show();
            this.loader = false;
        }, err => console.log(err));
    }

    public getActiveCode() {
        this.loader = true;

        this.pro.getActiveCode(this.type).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.activeCodePopup.hide();
                this.msg = "Active code have been send!";
            } else {
                this.msg = rsp.message;
            }
            this.informationModal.show();
            this.loader = false;
        }, err => console.log(err));
    }

    private view() {
        this.loader = true;

        this.pro.view().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.vm = rsp.result;
            } else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public back() {
        window.history.back();
    }
}