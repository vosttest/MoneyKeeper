import { Component, OnInit, ViewChild } from '@angular/core';
import { SettingProvider, CommonProvider } from '../../providers/provider';
import { ModalDirective, TimepickerModule } from 'ngx-bootstrap';
import { element } from 'protractor';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Setting } from '../../utilities/utility';

@Component({
    selector: 'app-setting',
    templateUrl: './setting.component.html',
    styleUrls: ['./setting.component.css']
})

export class SettingComponent implements OnInit {
    public data = [];
    public dataCurrency: any = [];
    public selectedCurrency: any = {};
    public reminder: any = {};
    public ismeridian = false;
    public isTime = false;
    public apiURL: string = "../../../../assets/img/currency/";
    public searchCurrency = '';
    public loginAuthen: any = {};
    public tranAuthen: any = {};
    public lock: any = {};
    public loader: boolean = false;
    public function: string = "reminder";
    public msg = '';
    @ViewChild('informationModal') public informationModal: ModalDirective;

    constructor(private pro: SettingProvider,
        private act: ActivatedRoute,
        private proCommon: CommonProvider) { }

    ngOnInit() {
        this.search();

        this.act.params.subscribe((params: Params) => {
            document.getElementById(this.function).style.display = "none";
            this.function = params["function"];
            document.getElementById(this.function).style.display = "block";
        });
    }

    private search() {
        this.loader = true;
        this.pro.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.data = rsp.result.data;

                this.data.forEach(element => {
                    switch (element.code) {
                        case Setting.CODE_REMINDER:
                            this.reminder.time = new Date(element.value);
                            this.reminder.status = element.status == 'ACT' ? true : false;
                            this.reminder.id = element.id;
                            break;
                        case Setting.CODE_CURRENCY:
                            this.selectedCurrency.id = element.id;
                            this.selectedCurrency.status = element.status;
                            this.selectedCurrency.value = element.value === '' || element.value == null ? 'VND' : element.value;
                            break;
                        case Setting.CODE_LOGIN:
                            this.loginAuthen.type = element.value == null ? '' : element.value;
                            this.loginAuthen.status = element.status == 'ACT' ? true : false;
                            this.loginAuthen.id = element.id;
                            break;
                        case Setting.CODE_TRANSACTION:
                            this.tranAuthen.type = element.value == null ? '' : element.value;
                            this.tranAuthen.status = element.status == 'ACT' ? true : false;
                            this.tranAuthen.id = element.id;
                            break;
                        case Setting.CODE_LOCK:
                            this.lock.status = element.status == 'ACT' ? true : false;
                            this.lock.id = element.id;
                            break;
                    }
                });
            }
            else {
                console.log(rsp.message);
            }
            this.loader = false;
        }, err => console.log(err));

        this.proCommon.search('Currency').subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.dataCurrency = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public changeTime() {
        this.isTime = false;
        if (this.reminder.status && this.reminder.time == null) {
            this.isTime = true;
            return;
        }
    }

    public saveReminder() {
        this.isTime = false;
        if (this.reminder.status && this.reminder.time == null) {
            this.isTime = true;
            return;
        }
        this.reminder.status = this.reminder.status ? 'ACT' : 'INA';
        this.reminder.time = this.reminder.time.toISOString();
        let x = {
            "id": this.reminder.id,
            "value": this.reminder.time,
            "status": this.reminder.status
        }
        this.pro.save(x).subscribe((rsp: any) => {
            if (rsp.status == "success") {
                this.msg = 'Save successfully!';
                this.search();
            }else{
                this.msg = rsp.message;
            }
            this.informationModal.show();
        }, err => console.log(err));
    }

    public saveCurrency() {
        let x = {
            id: this.selectedCurrency.id,
            value: this.selectedCurrency.value,
            status: this.selectedCurrency.status
        }

        this.pro.save(x).subscribe((rsp: any) => {
            if (rsp.status == "success") {
                this.msg = 'Save successfully!';
                this.search();
            } else {
                this.msg = rsp.message;
            }
            this.informationModal.show();
        }, err => console.log(err));
    }

    public saveLoginAuthen() {
        this.loginAuthen.status = this.loginAuthen.status ? 'ACT' : 'INA';

        let x = {
            "id": this.loginAuthen.id,
            "value": this.loginAuthen.type,
            "status": this.loginAuthen.status
        }

        this.pro.save(x).subscribe((rsp: any) => {
            if (rsp.status == "success" && rsp.message == "") {
                this.msg = 'Save successfully!';
                this.search();
            }else{
                this.msg = rsp.message;
            }
            this.informationModal.show();
        }, err => console.log(err));
    }

    public saveTranAuthen() {
        this.tranAuthen.status = this.tranAuthen.status ? 'ACT' : 'INA';

        let x = {
            "id": this.tranAuthen.id,
            "value": this.tranAuthen.type,
            "status": this.tranAuthen.status
        }

        this.pro.save(x).subscribe((rsp: any) => {
            if (rsp.status == "success" && rsp.message == "") {
                this.msg = 'Save successfully!';
                this.search();
            }else{
                this.msg = rsp.message;
            }
            this.informationModal.show();
        }, err => console.log(err));
    }

    public saveLock() {
        this.lock.status = this.lock.status ? 'ACT' : 'INA';

        let x = {
            "id": this.lock.id,
            "status": this.lock.status
        }

        this.pro.save(x).subscribe((rsp: any) => {
            if (rsp.status == "success" && rsp.message == "") {
                this.msg = 'Save successfully!';
                this.search();
            }else{
                this.msg = rsp.message;
            }
            this.informationModal.show();
        }, err => console.log(err));
    }
}