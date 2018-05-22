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
    public vm: any = { userName: '', password: '', clientKey: '', token: '' };
    public vm1: any = { email: '' }
    public loader: boolean = false;
    public message = '';
    public isDisabled = true;
    public token = '';

    @ViewChild('ForgotPassModal') public forgotPassModal: ModalDirective;
    @ViewChild('AccessTokenModal') public accessTokenModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signIn() {
        this.loader = true;

        this.pro.signIn(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                if (rsp.result.authen === 'F') {// When login authentication inactive
                    this.pro.saveAuth(rsp.result.key); // Response JWT -> log in success
                } else {
                    this.vm.clientKey = rsp.result.key; // Response client key
                    this.accessTokenModal.show();
                }
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public sendEmailVerificationLink(valid: boolean) {
        this.loader = true;
        let obj = { keyword: this.vm1.email };
        this.pro.verifyMail(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                alert("Please check mail box to change your password!");
            } else {
                let msg = rsp.message;
            }
            this.loader = false;
            this.forgotPassModal.hide();
        });
    }

    public proceedClick() {
        this.loader = true;

        this.vm.token = this.token;
        this.pro.signInWithToken(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                this.pro.saveAuth(rsp.result);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => { console.log(err); this.loader = false; });
    }

    public checkMaxlength(inputNumber: string, $event) {
        this.isDisabled = true;
        let element = $event.target.nextElementSibling;
        let value = $event.target.value;
        switch (inputNumber) {
            case 'input1':
                if (value && value.toString().length > 1) {
                    this.vm.codeNumber1 = value.toString().substring(0, 1);
                }
                else {
                    this.vm.codeNumber1 = value.toString();
                }
                break;

            case 'input2':
                if (value && value.toString().length > 1) {
                    this.vm.codeNumber2 = value.toString().substring(0, 1);
                }
                else {
                    this.vm.codeNumber2 = value.toString();
                }
                break;

            case 'input3':
                if (value && value.toString().length > 1) {
                    this.vm.codeNumber3 = value.toString().substring(0, 1);
                }
                else {
                    this.vm.codeNumber3 = value.toString();
                }
                break;

            case 'input4':
                if (value && value.toString().length > 1) {
                    this.vm.codeNumber4 = value.toString().substring(0, 1);
                }
                else {
                    this.vm.codeNumber4 = value.toString();
                }
                break;

            case 'input5':
                if (value && value.toString().length > 1) {
                    this.vm.codeNumber5 = value.toString().substring(0, 1);
                }
                else {
                    this.vm.codeNumber5 = value.toString();
                }
                break;

        }

        this.token = this.vm.codeNumber1 + this.vm.codeNumber2
            + this.vm.codeNumber3 + this.vm.codeNumber4
            + this.vm.codeNumber5;

        if (this.token.length == 5) {
            this.isDisabled = false;
        }

        // Check focus next input
        if (element == null || $event.key == "Backspace") {
            return;
        }
        else {
            element.focus();
        }
    }
}