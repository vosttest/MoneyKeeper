import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})

export class ChangePasswordComponent implements OnInit {
    public vm: any = { newPassword: '', confirmPassword: '', oldPassword: '' };
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
    public type = "password";
    public typeConfirm = "password";
    public typeOld = "password";
    public show = false;
    public showConfirm = false;


    @ViewChild('confirmModal') public confirmModal: ModalDirective;

    @ViewChild('messageModal') public messageModal: ModalDirective;

    @ViewChild('messageModal1') public messageModal1: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() { }

    public changePassword() {
        this.pro.changePassword(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/sign-in']);
            } else {
                this.messageModal.show();
                this.confirmModal.hide();
            }
        }, err => console.log(err));
    }

    public showConfirm1() {
        this.confirmModal.show();
    }

    public toggleShow(para?: string) {
        if (para == 'P') {
            this.show = !this.show;
            this.type = this.show ? "text" : "password";
        }
        else {
            this.showConfirm = !this.showConfirm;
            this.typeConfirm = this.showConfirm ? "text" : "password";
        }
    }
}