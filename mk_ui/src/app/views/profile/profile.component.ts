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

    @ViewChild('SendActiveModal') public sendActiveModal: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.infoUser();
    }

    public infoUser() {
        this.loader = true;
        this.pro.profileUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.vm = rsp.result.info;
            } else {
            }
            this.loader = false;
        }, err => console.log(err));
    }

    public save() {
        this.loader = true;
        this.pro.updateUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/dashboard']);
            } else {
            }
            this.loader = false;
        }, err => console.log(err));
    }

    public changeSend() {
        this.pro.getActivationCode(this.type).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.sendActiveModal.hide();
            } else {
            }
        }, err => console.log(err));
    }
}