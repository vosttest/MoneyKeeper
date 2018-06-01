import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VoucherEditComponent } from './voucher-edit.component';

const routes: Routes = [
    {
        path: '',
        component: VoucherEditComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class VoucherEditRoutingModule { }