import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { NgForm } from '@angular/forms';
import { HTTP } from '../../utilities/utility';

import * as $ from 'jquery';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
    public vm: any = { userName: "", password: "", clientKey: "", token: "" };
    public vm1: any = { email: "" }

    public loader: boolean = false;
    public isDisabled = true;
    public show = false;

    public message = "";
    public token = "";

    @ViewChild("forgotPassModal") public forgotPassModal: ModalDirective;
    @ViewChild("accessTokenModal") public accessTokenModal: ModalDirective;

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
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.message = "";

                if (!rsp.result.authen) {
                    this.pro.saveAuth(rsp.result.key); // save JWT
                } else {
                    this.vm.clientKey = rsp.result.key; // client key

                    this.vm.codeNumber1 = null;
                    this.vm.codeNumber2 = null;
                    this.vm.codeNumber3 = null;
                    this.vm.codeNumber4 = null;
                    this.vm.codeNumber5 = null;
                    this.vm.codeNumber6 = null;

                    this.isDisabled = true;
                    this.show = true;

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

    public cancelAccessTokenModal() {
        $("#codeNumber1").val(null);
        $("#codeNumber2").val(null);
        $("#codeNumber3").val(null);
        $("#codeNumber4").val(null);
        $("#codeNumber5").val(null);
        $("#codeNumber6").val(null);
        this.accessTokenModal.hide();
    }

    public sendEmailVerificationLink(valid: boolean) {
        if (!valid) {
            return;
        }

        this.loader = true;
        let obj = { keyword: this.vm1.email };
        this.pro.verifyMail(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                alert("Please check mail box to change your password!");
            } else {
                let msg = rsp.message;
            }
            this.loader = false;
            this.forgotPassModal.hide();
            this.show = false;
        });
    }

    public proceedClick() {
        this.loader = true;

        if (this.token.length < 6) {
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
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.message = "";
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
        let key = $event.key;

        switch (inputNumber) {
            case "input1":
                if (value.length > 1) {
                    $("#codeNumber1").val(key);
                }
                this.vm.codeNumber1 = key;
                break;

            case "input2":
                if (value.length > 1) {
                    $("#codeNumber2").val(key);
                }
                this.vm.codeNumber2 = key.toString();
                break;

            case "input3":
                if (value.length > 1) {
                    $("#codeNumber3").val(key);
                }
                this.vm.codeNumber3 = key.toString();
                break;

            case "input4":
                if (value.length > 1) {
                    $("#codeNumber4").val(key);
                }
                this.vm.codeNumber4 = key.toString();
                break;

            case "input5":
                if (value.length > 1) {
                    $("#codeNumber5").val(key);
                }
                this.vm.codeNumber5 = key.toString();
                break;

            case "input6":
                if (value.length > 1) {
                    $("#codeNumber6").val(key);
                }
                this.vm.codeNumber6 = key.toString();
                break;
        }

        this.token = this.vm.codeNumber1 + this.vm.codeNumber2
            + this.vm.codeNumber3 + this.vm.codeNumber4
            + this.vm.codeNumber5 + this.vm.codeNumber6;

        if (this.token.length == 6) {
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