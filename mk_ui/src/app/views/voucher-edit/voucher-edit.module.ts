import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';
import { VoucherEditRoutingModule } from './voucher-edit-routing.module';
import { VoucherEditComponent } from './voucher-edit.component';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        VoucherEditRoutingModule
    ],
    declarations: [
        VoucherEditComponent
    ],
    providers: [
    ],
})

export class VoucherEditModule { }