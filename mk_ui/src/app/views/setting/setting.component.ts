import { Component, OnInit, ViewChild } from '@angular/core';
import { SettingProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { TimepickerModule } from 'ngx-bootstrap';
import { element } from 'protractor';

@Component({
    selector: 'app-setting',
    templateUrl: './setting.component.html',
    styleUrls: ['./setting.component.css']
})

export class SettingComponent implements OnInit {
    public data = [];
    public reminder: any = {};
    public ismeridian=false;

    @ViewChild('reminderPopup') public reminderPopup: ModalDirective;
    @ViewChild('currencyPopup') public currencyPopup: ModalDirective;

    constructor(private pro: SettingProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.pro.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.data = rsp.result.data;

                this.data.forEach(element => {
                    if (element.code == 'SET01') {
                        this.reminder.time = element.value;
                        this.reminder.checked = element.status == 'ACT' ? true : false;
                        this.reminder.id=element.id;
                        console.log(this.reminder.checked);
                    }
                });
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public showDetailSetting(code: string) {
        switch (code) {
            case 'SET01':
                this.reminderPopup.show();
                break;
            case 'SET02':
                this.currencyPopup.show();
                break;
        }
    }

    public closeModal(type: string) {
        switch (type) {
            case 'reminder':
                this.reminderPopup.hide();
                break;
            case 'currency':
                this.currencyPopup.hide();
        }
    }

    public changeTime() {
       
    }
}