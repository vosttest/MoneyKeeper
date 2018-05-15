import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AccountComponent } from './views/account/account.component';
import { AccountAddComponent } from './views/account-add/account-add.component';
import { AccountEditComponent } from './views/account-edit/account-edit.component';
import { CategoryComponent } from './views/category/category.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { SettingComponent } from './views/setting/setting.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { VoucherComponent } from './views/voucher/voucher.component';

import { ForgotPasswordComponent } from './views/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './views/change-password/change-password.component';

const routes: Routes = [
    { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
    { path: 'account', component: AccountComponent },
    { path: 'account-add', component: AccountAddComponent },
    { path: 'account-edit/:id', component: AccountEditComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'setting', component: SettingComponent },
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'voucher', component: VoucherComponent },
    { path: 'forgot-password', component: ForgotPasswordComponent },
    { path: 'change-password', component: ChangePasswordComponent },
    { path: '**', redirectTo: 'sign-in', pathMatch: 'full' },
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule],
    declarations: []
})

export class AppRoutingModule { }