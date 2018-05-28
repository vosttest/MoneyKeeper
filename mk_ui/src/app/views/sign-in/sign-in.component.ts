import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { NgForm } from '@angular/forms';

import * as $ from 'jquery';

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

    @ViewChild('forgotPassModal') public forgotPassModal: ModalDirective;
    @ViewChild('accessTokenModal') public accessTokenModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signIn() {
        this.loader = true;

        let obj = {
            userName: this.vm.userName,
            password: this.vm.password,
            clientKey: this.vm.clientKey,
            token: this.vm.token,
            sendToken: true
        };

        this.pro.signIn(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                // authen: F: without login authen, T: with login authen
                if (!rsp.result.authen) {// When login authentication inactive
                    this.pro.saveAuth(rsp.result.key); // Response JWT -> log in success
                } else {
                    this.vm.clientKey = rsp.result.key; // Response client key
                    this.vm.codeNumber1 = this.vm.codeNumber2 = this.vm.codeNumber3 = this.vm.codeNumber4 = this.vm.codeNumber5 = null;
                    this.isDisabled = true;
                    this.accessTokenModal.show();
                    setTimeout(function () {
                        document.getElementById("codeNumber1").focus();
                    }, 500);
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

        if (this.token.length < 5) {
            return;
        }

        let obj = {
            userName: this.vm.userName,
            password: this.vm.password,
            clientKey: this.vm.clientKey,
            token: this.token,
            sendToken: false
        };
        this.pro.signIn(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                this.pro.saveAuth(rsp.result.key);
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
        let value2 = $event.key;
        switch (inputNumber) {
            case 'input1':
                if (value.length > 1) {
                    $('#codeNumber1').val(value2);
                }
                this.vm.codeNumber1 = value2;
                break;

            case 'input2':
                if (value.length > 1) {
                    $('#codeNumber2').val(value2);
                }
                this.vm.codeNumber2 = value2.toString();
                break;

            case 'input3':
                if (value.length > 1) {
                    $('#codeNumber3').val(value2);
                }
                this.vm.codeNumber3 = value2.toString();
                break;

            case 'input4':
                if (value.length > 1) {
                    $('#codeNumber4').val(value2);
                }
                this.vm.codeNumber4 = value2.toString();
                break;

            case 'input5':
                if (value.length > 1) {
                    $('#codeNumber5').val(value2);
                }
                this.vm.codeNumber5 = value2.toString();
                break;

        }

        this.token = this.vm.codeNumber1 + this.vm.codeNumber2
            + this.vm.codeNumber3 + this.vm.codeNumber4
            + this.vm.codeNumber5;
        console.log(this.vm.codeNumber1);
        console.log(this.vm.codeNumber2);
        console.log(this.vm.codeNumber3);
        console.log(this.vm.codeNumber4);
        console.log(this.vm.codeNumber5);


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