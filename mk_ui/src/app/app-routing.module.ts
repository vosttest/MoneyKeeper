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
import { LayoutComponent } from './views/layout/layout.component';

const routes: Routes = [
    { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
    { path: 'sign-in', loadChildren: './views/sign-in/sign-in.module#SignInModule' },
    { path: 'sign-up', loadChildren: './views/sign-up/sign-up.module#SignUpModule' },
    { path: 'forgot-password', loadChildren: './views/forgot-password/forgot-password.module#ForgotPasswordModule' },
    {
        path: '',
        component: LayoutComponent,
        children:
            [
                {
                    path: 'account',
                    loadChildren: './views/account/account.module#AccountModule'
                }, {
                    path: 'account-add',
                    loadChildren: './views/account-add/account-add.module#AccountAddModule'
                }, {
                    path: 'account-edit/:id',
                    loadChildren: './views/account-edit/account-edit.module#AccountEditModule'
                }, {
                    path: 'dashboard',
                    loadChildren: './views/dashboard/dashboard.module#DashboardModule'
                }, {
                    path: 'category',
                    loadChildren: './views/category/category.module#CategoryModule'
                }, {
                    path: 'setting',
                    loadChildren: './views/setting/setting.module#SettingModule'
                }, {
                    path: 'voucher',
                    loadChildren: './views/voucher/voucher.module#VoucherModule'
                }, {
                    path: 'change-password',
                    loadChildren: './views/change-password/change-password.module#ChangePasswordModule'
                }
            ]
    },
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