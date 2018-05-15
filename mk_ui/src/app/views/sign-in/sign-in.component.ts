import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
    public vm: any = { userName: '', password: '' };
    public vm1: any = { email: '' }
    public loader: boolean = false;
    public message = '';

    @ViewChild('ForgotPassModal') public forgotPassModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signIn() {
        this.loader = true;

        this.pro.signIn(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                this.pro.saveAuth(rsp.result);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public sendEmailVerificationLink(valid: boolean) {
        let obj = { keyword: this.vm1.email };
        this.pro.verifyMail(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                alert("Please check mail box to change your password!");
            } else {
                let msg = rsp.message;
                alert(msg);
            }
            this.forgotPassModal.hide();
        });
    }
}