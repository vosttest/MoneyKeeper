import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule } from 'ngx-bootstrap';
import { BsDatepickerModule } from 'ngx-bootstrap';

import { AccountEditRoutingModule } from './account-edit-routing.module';
import { AccountEditComponent } from './account-edit.component';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        AccountEditRoutingModule
    ],
    declarations: [
        AccountEditComponent
    ]
})

export class AccountEditModule { }