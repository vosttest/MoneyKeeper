import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';

import { AccountRoutingModule } from './account-routing.module';
import { AccountComponent } from './account.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FilterPipe } from '../../utilities/filter.pipe';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        AccountRoutingModule
    ],
    declarations: [
        AccountComponent,
        FilterPipe
    ]
})

export class AccountModule { }