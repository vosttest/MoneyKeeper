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
    public loader: boolean;
    public msg: string = "";
    public success: boolean = false;

    @ViewChild('confirmModal') public confirmModal: ModalDirective;
    @ViewChild('messageModal') public messageModal: ModalDirective;
    @ViewChild('informationModal') public informationModal: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() { }

    public changePassword() {
        this.loader = true;

        this.pro.changePassword(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.confirmModal.hide();
                this.success = true;
                this.msg = "Save successfully!";
            } else {
                this.confirmModal.hide();
                this.success = false;
                this.msg = "Current password is wrong!!!";
            }
            this.informationModal.show();

            this.loader = false;
        }, err => console.log(err));
    }

    public showConfirm1() {
        if (this.vm.oldPassword == '' || this.vm.newPassword == '' || this.vm.confirmPassword == '') {
            this.confirmModal.hide();
        } else {
            this.confirmModal.show();
        }
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