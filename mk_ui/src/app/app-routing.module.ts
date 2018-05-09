import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { CategoryComponent } from './views/category/category.component';
import { VoucherComponent } from './views/voucher/voucher.component';
import { ManageAccountComponent } from './views/manage-account/manage-account.component';
import { ManageAccountAddComponent } from './views/manage-account-add/manage-account-add.component';
import { ManageAccountEditComponent } from './views/manage-account-edit/manage-account-edit.component';

const routes: Routes = [
    { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'insert-acc', component: ManageAccountComponent },
    { path: 'add-acc', component: ManageAccountAddComponent },
    { path: 'edit-acc', component: ManageAccountEditComponent },
    { path: 'voucher', component: VoucherComponent },
    { path: '**', redirectTo: 'sign-in', pathMatch: 'full' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})

export class AppRoutingModule { }