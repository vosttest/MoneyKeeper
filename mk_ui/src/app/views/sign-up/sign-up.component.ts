import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider, SettingProvider, CommonProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { Router } from '@angular/router';
import { Const, HTTP, Type, Setting } from '../../utilities/utility';

@Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['../sign-in/sign-in.component.css', './sign-up.component.css']
})

export class SignUpComponent implements OnInit {
    public vm: any = { userName: "", password: "" };
    public loader: boolean = false;
    public message = "";
    public show = true;
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
    public selectedCurrency = { id: 0, status: '', value: '' };
    public selectedLanguage = { id: 0, status: '', value: '' };
    public dataCurrency: any = [];
    public dataLanguage: any = [];

    public imgURL = "../../../../assets/img/";
    public imgCurrencyURL = this.imgURL + "currency/";
    public imgLanguageURL = this.imgURL + "language/";

    public searchCurrency = '';
    public searchLanguage = '';

    @ViewChild("defaultModal") public defaultModal: ModalDirective;

    constructor(
        private rou: Router,
        private pro: UserProvider,
        private proSetting: SettingProvider,
        private proCommon: CommonProvider) { }

    ngOnInit() { }

    public signUp() {
        this.loader = true;

        this.pro.signUp(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.pro.saveAuth(rsp.result, false);

                this.proSetting.search().subscribe((rsp: any) => {
                    if (rsp.status === HTTP.STATUS_SUCCESS) {
                        this.show = false;
                        let data = rsp.result.data;

                        data.forEach(element => {
                            switch (element.code) {
                                case Setting.CODE_CURRENCY:
                                    this.selectedCurrency.id = element.id;
                                    this.selectedCurrency.status = element.status;
                                    this.selectedCurrency.value = element.value === "" || element.value === null ? "VND" : element.value;
                                    break;
                                case Setting.CODE_LANGUAGE:
                                    this.selectedLanguage.id = element.id;
                                    this.selectedLanguage.status = element.status;
                                    this.selectedLanguage.value = element.value === "" || element.value === null ? "vi-vn" : element.value;
                                    break;
                            }
                        });
                        this.search();
                    }
                    else {
                        console.log(rsp.message);
                    }

                    this.loader = false;
                }, err => console.log(err));
            } else {
                this.message = rsp.message;
            }
        }, err => console.log(err));
    }

    public proceedClick() {
        this.saveLanguage();
    }

    private saveLanguage() {
        let x = {
            id: this.selectedLanguage.id,
            value: this.selectedLanguage.value,
            status: this.selectedLanguage.status
        }
        this.proSetting.save(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.saveCurrency();
            } else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    private saveCurrency() {
        this.loader = true;

        let x = {
            id: this.selectedCurrency.id,
            value: this.selectedCurrency.value,
            status: this.selectedCurrency.status
        }
        this.proSetting.save(x, true).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.rou.navigate(['/dashboard']);
            } else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private search() {
        this.getCurrency();
    }

    private getCurrency() {
        this.proSetting.exrate().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.proCommon.search(Type.CURRENCY).subscribe((rsp: any) => {
                    if (rsp.status === HTTP.STATUS_SUCCESS) {
                        this.dataCurrency = rsp.result.data;
                        this.getLanguage();
                    }
                    else {
                        console.log(rsp.message);
                    }
                }, err => console.log(err));
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private getLanguage() {
        this.proCommon.search(Type.LANGUAGE).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.dataLanguage = rsp.result.data;
                this.defaultModal.show();
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }
}